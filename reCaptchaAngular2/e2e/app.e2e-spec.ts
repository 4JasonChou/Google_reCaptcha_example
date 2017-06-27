import { ReCaptchaWebPage } from './app.po';

describe('re-captcha-web App', () => {
  let page: ReCaptchaWebPage;

  beforeEach(() => {
    page = new ReCaptchaWebPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
