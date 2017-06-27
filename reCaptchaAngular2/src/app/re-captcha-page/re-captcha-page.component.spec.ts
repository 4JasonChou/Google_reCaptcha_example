import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReCaptchaPageComponent } from './re-captcha-page.component';

describe('ReCaptchaPageComponent', () => {
  let component: ReCaptchaPageComponent;
  let fixture: ComponentFixture<ReCaptchaPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReCaptchaPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReCaptchaPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
