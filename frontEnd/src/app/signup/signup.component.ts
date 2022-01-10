import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { SignupService } from './signup.service';
import {Router} from '@angular/router';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-signup',
  templateUrl: './Signuppage.html',
  styleUrls: ['./signupstyle.css']
})

export class SignupComponent implements OnInit {
  submitButton: any;
  UserInput = '';
  accountInput='';
  passwordInput='';
  RetrypasswordInput = '';
  badRequest = '';
  datas: any;
  isNameValid: boolean;
  isAccountValid: boolean;
  isPasswordValid: boolean;
  isSamePassword: boolean;
  constructor(private router: Router, private signupService: SignupService, @Inject(DOCUMENT) document) { }

  ngOnInit(): void {
    this.submitButton = document.getElementById('submit');
    this.submitButton.disabled = true;
  }

  redirectTo(url){
    this.router.navigateByUrl(url.toString());
  }

  RoutetoLoginPage(){
    this.redirectTo("LoginPage");
  }

  SignUpCheck(){
    const UserSignUpData = {
      userName:undefined,
      account: undefined,
      password: undefined
    };
    UserSignUpData.userName  = this.UserInput;
    UserSignUpData.account  = this.accountInput;
    UserSignUpData.password = this.passwordInput;

    const data = JSON.stringify(UserSignUpData);
    this.signupService.verifySignUpUserAccount(data).subscribe(
    request => {
        this.datas = request;
        if (this.datas.isSuccess == "true"){
          alert("註冊成功!轉至登入頁面")
          this.redirectTo("LoginPage");
          console.log(this.datas.redirect);
        }
        else{
          this.badRequest = "此帳號已被使用，請重新命名";
        }
      }
    );
  }

  onPasswordChange(value: any) {
    this.isPasswordValid = this.isInputValid(value);
    this.isAllInputValid();
  }

  onAccountChange(value: any) {
    this.isAccountValid = this.isInputValid(value);
    this.isAllInputValid();
  }

  onNameChange(value: any) {
    this.isNameValid = this.isInputValid(value);
    this.isAllInputValid();
  }

  isInputValid(value: string) {
    if (value === "") {
      return false;
    }
    else {
      return true;
    }
  }

  isAllInputValid() {
    if (this.isPasswordValid && this.isAccountValid && this.isNameValid && this.isSamePassword) {
      this.submitButton.disabled = false;
    }
    else {
      this.submitButton.disabled = true;
    }
  }

  onRetryPasswordChange(value: any) {
    this.isSamePassword = value == this.passwordInput;
    this.isAllInputValid();
  }
}
