import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReCaptchaModule } from 'angular2-recaptcha';

import { AppComponent } from './app.component';
import { ReCaptchaPageComponent } from './re-captcha-page/re-captcha-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  // 設定於此 假如 http://localhost:4200/Index ↓
  { path: 'Index', component: HomePageComponent },
  {
    path: 'Second',
    component: ReCaptchaPageComponent ,
    data: { title: 'Second Title' }
  },
  { path: '**', component: HomePageComponent }
] ; 


@NgModule({
  declarations: [
    AppComponent,
    ReCaptchaPageComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    ReCaptchaModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
