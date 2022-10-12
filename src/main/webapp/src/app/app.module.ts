import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgChartsModule } from 'ng2-charts';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageAComponent } from './components/page-a/page-a.component';
import { PageBComponent } from './components/page-b/page-b.component';
import { KeyReaderDirective } from './key-reader.directive';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatButtonModule} from "@angular/material/button";
import { LearningComponent } from './components/learning/learning.component';
import { FormsModule } from '@angular/forms';
import { TestsComponent } from './components/tests/tests.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatInputModule} from "@angular/material/input";
import { InfoComponent } from './components/info/info.component';
import {MatExpansionModule} from "@angular/material/expansion";

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    PageAComponent,
    PageBComponent,
    KeyReaderDirective,
    LearningComponent,
    TestsComponent,
    InfoComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgChartsModule,
        BrowserAnimationsModule,
        MatSidenavModule,
        MatIconModule,
        MatDividerModule,
        HttpClientModule,
        MatButtonModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient],
            },
            defaultLanguage: 'pl',
        }),
        MatProgressSpinnerModule,
        FormsModule,
        MatToolbarModule,
        MatInputModule,
        MatExpansionModule
    ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
