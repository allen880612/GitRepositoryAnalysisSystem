import { Component, OnInit } from '@angular/core';
import {SonarqubeService} from "./sonarqube.service";
import {Router} from "@angular/router";
import {GetProjectInfoService} from "../choose-project/get-project-info.service";

class JSONObject {
}

@Component({
  selector: 'app-sonarqube',
  templateUrl: './sonarqube.component.html',
  styleUrls: ['./sonarqube.component.css']
})
export class SonarqubeComponent implements OnInit {
  coverage: any;
  bugs: any;
  codeSmell: any;
  duplication: any;
  datas: any;
  ProjectID: any;
  obj: JSONObject;

  constructor(private router: Router, private SonarqubeService: SonarqubeService) {}

  ngOnInit(): void {
    this.ProjectID = window.sessionStorage.getItem('ChosenProjectID');
    this.getProjectID();
    this.datas=this.obj;
  }

  getCommitTrend() {
    this.router.navigate(['gitanalysis']);
  }

  getProjectID() {
    this.obj = new JSONObject();
    this.obj = {
      coverage:0.87,
      bugs:20,
      codeSmell:30,
      duplication:0.56
    };
    const UserProjectID = {
      projectId: undefined,
    };
    UserProjectID.projectId  = this.ProjectID;
    // const data = JSON.stringify(UserProjectID);
    // this.SonarqubeService.getUserSonarQube(data).subscribe(
    //   request => {
    //     this.datas = request;
    //     console.log(this.datas);
    //   }
    // );
  }
}
