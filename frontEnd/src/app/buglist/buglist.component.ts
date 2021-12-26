import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {IssueTrackService} from "../issue-track/issue-track.service";
import {BuglistService} from "./buglist.service";

class JSONObject {
}

@Component({
  selector: 'app-buglist',
  templateUrl: './buglist.component.html',
  styleUrls: ['./buglist.component.css']
})
export class BuglistComponent implements OnInit {
  datas: any;
  titles = [];
  severities = [];
  efforts = [];
  owner: any;
  repo: any;
  obj1: JSONObject;
  obj2: JSONObject;
  obj3: JSONObject;
  step = 0;

  constructor(private router: Router, private BuglistService: BuglistService, private acrouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.repo = window.sessionStorage.getItem('repoName');
    this.owner = window.sessionStorage.getItem('owner');

    console.log(this.repo);
    console.log(this.owner);

    this.getBugList();
  }

  // tslint:disable-next-line:typedef
  setStep(index: number) {
    this.step = index;
  }

  // tslint:disable-next-line:typedef
  nextStep() {
    this.step++;
  }

  // tslint:disable-next-line:typedef
  prevStep() {
    this.step--;
  }

  getBugList() {
    const bugListData = {
      owner: undefined,
      repo: undefined
    };
    bugListData.owner = this.owner;
    bugListData.repo = this.repo;

    this.obj1 = new JSONObject();
    this.obj1 = {
      titles:"bug1",
      severities:"serverties1",
      efforts:"effort1",
    };

    this.obj2 = new JSONObject();
    this.obj2 = {
      titles:"bug2",
      severities:"serverties2",
      efforts:"effort2",
    };
    this.obj3 = new JSONObject();
    this.obj3 = {
      titles:"bug3",
      severities:"serverties3",
      efforts:"effort3",
    };

    this.titles.push("bug1");
    this.titles.push("bug2");
    this.titles.push("bug3");

    this.severities.push("serverties1");
    this.severities.push("serverties2");
    this.severities.push("serverties3");

    this.efforts.push("effort1");
    this.efforts.push("effort2");
    this.efforts.push("effort3");

    // const data = JSON.stringify(bugListData);
    // this.BuglistService.getBugListService(data).subscribe(
    //   request => {
    //     this.datas = request;
    //     for (const temp of this.datas){
    //       this.titles.push(temp.title);
    //       this.seveseverities.push(temp.body);
    //       this.efforts.push(temp.state);
    //     }
    //   }
    // );
  }

}
