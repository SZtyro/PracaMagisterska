package pl.sztyro.pracamagisterska.rest;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

    private static final Logger _logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public Object getUser(HttpServletRequest request) throws IOException {

        Map <String, Object> obj = new HashMap<>();
        JSONObject profile = getProfile(request);
        obj.put("name", profile.get("name"));
        obj.put("picture", profile.get("picture"));
        return obj;

    }

    public JSONObject getProfile(HttpServletRequest request) {
        return new JSONObject(new Gson().toJson(request.getUserPrincipal()))
                .getJSONObject("userAuthentication")
                .getJSONObject("details");
    }

    @PostMapping("/logout")
    public void googleLogout(HttpServletRequest request) {
        _logger.info("Logging out user: " + request.getRemoteUser());
        request.getSession().invalidate();
    }
}
