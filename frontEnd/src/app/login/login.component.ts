import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
import {LoginService} from './login.service';
import { environment as env } from '../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrls: ['./loginstyle.css']
})

export class LoginComponent implements OnInit {
  name = 'Hello';
  accountInput: any;
  passwordInput: any;
  datas: any;
  badRequest:any;
  code:any;
  Username = "";
  UserID = "";
  constructor(private router: Router, private loginService: LoginService , private acrouter: ActivatedRoute) {
   }

  ngOnInit(): void {}

  // tslint:disable-next-line:typedef
  redirectTo(url){
    this.router.navigateByUrl(url.toString());
  }

  // tslint:disable-next-line
  logInCheck() {
    const UserLoginData = {
      account: undefined,
      password: undefined
    };

    UserLoginData.account  = this.accountInput;
    UserLoginData.password = this.passwordInput;

    const data = JSON.stringify(UserLoginData);
    this.loginService.verifyUserLoginData(data).subscribe(
      request => {
        this.datas = request;
        if (this.datas.redirect){
          this.router.navigate([this.datas.redirect.toString()]);
          console.log("userId =",this.datas.userId.toString());
          sessionStorage.setItem('Username', this.datas.userName.toString());
          sessionStorage.setItem('UserID', this.datas.userId.toString());
        }
        else{
          this.badRequest = '帳號或密碼錯誤';
        }
      }
    );
  }

  RoutetoSignup(){
    this.redirectTo('signup');
  }

  RoutetoGitOAuth(){
    const params = {
      client_id: '27ea0af1aeabd39a7d5d',
      // redirect_uri: 'oauth2/github/callback',
      scope: 'repo',
      state: 'RRRRRRRRR',
      allow_signup: 'true'
    };
    console.log('clientId: ', env.clientId);
    const url = 'https://github.com/login/oauth/authorize?';
    const searchParams = new URLSearchParams(params);
    const authUrl = url + searchParams.toString();
    console.log(authUrl);
    window.location.href = authUrl;
  }
}
