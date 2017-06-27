import { Component, OnInit } from '@angular/core';
import { Injectable , ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-re-captcha-page',
  templateUrl: './re-captcha-page.component.html',
  styleUrls: ['./re-captcha-page.component.css']
})
export class ReCaptchaPageComponent implements OnInit {

  mRes:any;
  
  constructor(private ref: ChangeDetectorRef) { }

  ngOnInit() {
    this.render();
    window['verified'] = (response) => this.verified(response)
  }
  render(){
        var script = document.createElement('script');
        script.src = 'https://www.google.com/recaptcha/api.js';
        script.async = true;
        script.defer = true;
        document.body.appendChild(script);
   }

  verified(response){
      console.log(response);
      this.mRes = response;
      this.ref.detectChanges(); 
  }
}
