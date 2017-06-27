import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { ReCaptchaComponent } from 'angular2-recaptcha';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @ViewChild(ReCaptchaComponent) captcha: ReCaptchaComponent;
  handleCaptcha()
  {
    var token = this.captcha.getResponse();
    console.log(token);
  }
}
