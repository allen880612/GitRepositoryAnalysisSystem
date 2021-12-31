import { Component, Input,OnInit } from '@angular/core';
import {Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  // homepageurl = "homepage";
  homepageurl="choose-project";
  ProjectCreatwpageurl = "createproject";
  ProjectOverviewpageurl = "choose-project";
  Logoutpageurl = "LoginPage";
  UserName:any;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.UserName = window.sessionStorage.getItem('Username');
  }

  redirectTo(url){
    this.router.navigateByUrl(url.toString());
  }
  NavitoCreateProject(){
    this.redirectTo("createproject");
  }

  NavitoProjectOverview(){
    this.redirectTo("choose-project");
    // window.location.reload();
  }

  NavitoProjectAnalysis(){
    this.redirectTo("project-analysis");
    // window.location.reload();
  }

  NavitoLogout(){
    this.redirectTo("LoginPage");
    window.sessionStorage.clear();

  }




}
