package pl.sztyro.pracamagisterska.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sztyro.pracamagisterska.model.BiometryChunk;
import pl.sztyro.pracamagisterska.model.MembershipFunction;
import pl.sztyro.pracamagisterska.model.User;
import pl.sztyro.pracamagisterska.service.BiometryChunkService;
import pl.sztyro.pracamagisterska.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class BaseController {

    /*Jeśli powyżej to użytkownik zgodny*/
    final Double threshold = 0.75;
    /*Jeśli powyżej to aktualizacja modelu*/
    final Double updateThreshold = 0.65;
    final Double b = 1.5;
    final Double defaultWidth = 70.0;
    final int measurementLimit = 21;
    final int measurementMinimum = 2;
    final int incompatibilitiesCountLimit = 5;


    @Autowired
    BiometryChunkService biometryChunkService;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @PostMapping("/biometry")
    public void checkBehavior(@RequestBody BiometryChunk[] chunks, HttpServletRequest request) {

        String email = userController.getProfile(request).getString("email");
        Optional<User> optionalUser = userService.getCurrentUserByEmail(email);

        if (!optionalUser.isPresent()) {
            userService.userRepository.save(new User(email));
            System.out.println("Not logged");
            //throw new HTTPException(401);
        } else {
            checkLoggedUser(optionalUser.get(), chunks, request);
        }

    }

    @GetMapping("/biometry")
    public List<BiometryChunk> getBiometry(HttpServletRequest request){
        String email = userController.getProfile(request).getString("email");
        Optional<User> optionalUser = userService.getCurrentUserByEmail(email);

        if(optionalUser.isPresent())
            return this.biometryChunkService.getUserChunks(optionalUser.get());
        else return null;
    }

    private void checkLoggedUser(User user, BiometryChunk[] chunksToCheck, HttpServletRequest request) {
        for (BiometryChunk newChunk : chunksToCheck) {
            BiometryChunk oldChunk = biometryChunkService.getByPairAndUser(newChunk.getPair(), user);
            //System.out.println(chunk.getTimes());
            if (oldChunk == null) {
                //Użytkownik nie ma takiej pary -> przypisujemy
                BiometryChunk biometryChunk = new BiometryChunk(user, newChunk.getPair(), newChunk.getTimes());
                biometryChunk.setMembershipFunction(createFunctionForPair(biometryChunk));
                biometryChunkService.saveChunk(biometryChunk);
            } else {
                if (newChunk.getTimes().size() > 0)
                    if(!checkAndUpdate(oldChunk, newChunk)){
                        user.setIncompatibilitiesCount(user.getIncompatibilitiesCount() + 1);
                        if(user.getIncompatibilitiesCount() > 4){
                            request.getSession().invalidate();
                            user.setIncompatibilitiesCount(0);
                            userService.saveUser(user);
                        }
                    }
            }
        }
    }

    private boolean checkAndUpdate(BiometryChunk oldChunk, BiometryChunk newChunk) {

        MembershipFunction function = oldChunk.getMembershipFunction();

        //List<String> notInRange = new ArrayList<>();
        //Jeśli w bazie jest mniej wynikow dla danej pary to przypisanie bez sprawdzenia
        if (oldChunk.getTimes().size() < measurementLimit)
            mergeAndFilter(oldChunk, newChunk);
        else
            //Sprawdzenien nowych czasow czy sa zgodne ze starym modelem
            for (String x : newChunk.getTimes()) {

            }

        int allNewTimes = newChunk.getTimes().size();

        //Tylko jeśli jest wiecej pomiarow
        if(allNewTimes >= measurementMinimum){
            //Odrzucenie skrajnych wynikow
            double avgOldTimes = mergeAndFilter(null, newChunk).stream().mapToDouble(Double::parseDouble).average().getAsDouble();
            double functionResult = getResult(oldChunk.getMembershipFunction(), avgOldTimes);

            if (functionResult > threshold|| oldChunk.getTimes().size() < measurementLimit) {
                System.out.println("+");
                List<String> newTimes = mergeAndFilter(oldChunk, newChunk);
                oldChunk.setTimes(newTimes);
                //Aktualizacja pary
                this.biometryChunkService.deleteFunction(oldChunk.getMembershipFunction());
                oldChunk.setMembershipFunction(createFunctionForPair(oldChunk));
                biometryChunkService.biometryChunkRepository.save(oldChunk);

            } else {
                if(functionResult < updateThreshold){

                    System.out.println("Wylogowanie dla pary: " + oldChunk.getPair());
                    return false;

                }else {
                    System.out.println("Brak konsekwencji");
                    return true;
                }

            }
        }
        return true;

    }

    private List<String> mergeAndFilter(BiometryChunk oldChunk, BiometryChunk newChunk) {
        List<String> newTimes = new ArrayList<>();
        List<String> merged;
        if (oldChunk == null) {
            merged = newChunk.getTimes();
        } else {
            //Laczenie obu tablic
            merged = Stream.concat(oldChunk.getTimes().stream(), newChunk.getTimes().stream())
                    .collect(Collectors.toList());
        }
        int s = merged.size();
        int half;
        //Jesli jest wiecej wynikkow to trzeba te wziac tylko najczestsze
        if (s > measurementLimit) {
            if (s % 2 == 1)
                half = (s - 1) / 2;
            else
                half = s / 2;

            int halfLimit = (measurementLimit - 1) / 2;

            for (int i = half - halfLimit; i <= half + halfLimit; i++) {
                newTimes.add(merged.get(i));
            }
        } else {
            newTimes = merged;
        }
        return sortTimes(newTimes);
    }

    private MembershipFunction createFunctionForPair(BiometryChunk chunk) {
        //a - szerokosc
        //b - nachylenie
        //c - środek
        Double a = defaultWidth, c;
        //sortowanie
        chunk.setTimes(sortTimes(chunk.getTimes()));
        List<String> sorted = chunk.getTimes();
        /*  srednia roznica pomiedzy pomiarami. Jeśli roznica jest mala to oznacza ze wyniki sa ciasno - uzytkownik
         *   pisze barrdzo podobnie = b wyzszy
         */

        Double avgDiff = null;
        if (sorted.size() < 2)
            avgDiff = 0.0;
        else if (sorted.size() == 2)
            avgDiff = Double.parseDouble(sorted.get(1)) - Double.parseDouble(sorted.get(0));
        else {
            List<Double> diff = new ArrayList<>();
            for (int i = 0; i < sorted.size() - 1; i++) {
                diff.add(Double.parseDouble(sorted.get(i + 1)) - Double.parseDouble(sorted.get(i)));
            }
            OptionalDouble x = diff.stream().mapToDouble(v -> v).average();
            avgDiff = x.isPresent() ? x.getAsDouble() : 0;

            //a = Math.abs(Double.parseDouble(sorted.get(sorted.size() - 1)) - Double.parseDouble(sorted.get(0)));
        }

        //b = avgDiff / 4;
        //b = Math.abs(Math.pow(avgDiff, -1)) * 10;


        OptionalDouble average = sorted.stream().mapToDouble(Double::parseDouble).average();
        c = average.isPresent() ? average.getAsDouble() : null;

        return new MembershipFunction(a, b, c);
    }

    private List<String> sortTimes(List<String> times) {
        List<String> collect = times.stream().sorted().collect(Collectors.toList());

        return collect;
    }

    private double getResult(MembershipFunction function, Double x) {
        return (1 / (1 + Math.pow(Math.abs((x - function.getC()) / function.getA()), 2 * function.getB())));
    }

}
