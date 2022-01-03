import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {IssueTrackService} from "../issue-track/issue-track.service";
import {BuglistService} from "./buglist.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ThemePalette} from "@angular/material/core";

class JSONObject {
}

export  interface Task {
  name: string;
  completed: boolean;
  color: ThemePalette;
  subtasks?: Task[];
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
  components = [];
  redirectUrls = [];
  types = [];
  issue_count: string;
  effort_total: string;

  ProjectID: string;
  repo: any;
  obj1: JSONObject;
  obj2: JSONObject;
  obj3: JSONObject;
  step = 0;

  sonarGroup: FormGroup;

  task: Task = {
    name: 'Select ALL',
    completed: false,
    color: 'primary',
    subtasks: [
      {name: 'BUG', completed: false, color: 'primary'},
      {name: 'CODE_SMELL', completed: false, color: 'accent'},
      {name: 'VULNERABILITY', completed: false, color: 'warn'},
    ],
  };
  allComplete: boolean = false;

  constructor(private router: Router, private BuglistService: BuglistService, private acrouter: ActivatedRoute, fb: FormBuilder) {
    this.sonarGroup = fb.group({
      BUG: true,
      CODE_SMELL: false,
      VULNERABILITY: false,
    });
  }

  ngOnInit(): void {
    this.ProjectID = window.sessionStorage.getItem('ChosenProjectID');
    console.log(this.ProjectID);
    this.getBugList();
  }

  updateAllComplete() {
    this.allComplete = this.task.subtasks != null && this.task.subtasks.every(t => t.completed);
  }

  someComplete(): boolean {
    if (this.task.subtasks == null) {
      return false;
    }
    return this.task.subtasks.filter(t => t.completed).length > 0 && !this.allComplete;
  }

  setAll(completed: boolean) {
    this.allComplete = completed;
    if (this.task.subtasks == null) {
      return;
    }
    this.task.subtasks.forEach(t => (t.completed = completed));
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
      projectId: this.ProjectID,
    };

    // this.obj1 = new JSONObject();
    // this.obj1 = {
    //   titles:"bug1",
    //   severities:"serverties1",
    //   efforts:"effort1",
    // };
    //
    // this.obj2 = new JSONObject();
    // this.obj2 = {
    //   titles:"bug2",
    //   severities:"serverties2",
    //   efforts:"effort2",
    // };
    // this.obj3 = new JSONObject();
    // this.obj3 = {
    //   titles:"bug3",
    //   severities:"serverties3",
    //   efforts:"effort3",
    // };
    //
    // this.titles.push("abcdefghijklmnopqrstuvwxyz");
    // this.titles.push("bug2");
    // this.titles.push("bug3");
    //
    // this.severities.push("serverties1");
    // this.severities.push("serverties2");
    // this.severities.push("serverties3");
    //
    // this.efforts.push("effort1");
    // this.efforts.push("effort2");
    // this.efforts.push("effort3");

    const data = JSON.stringify(bugListData);
    this.BuglistService.getBugListService(data).subscribe(
      request => {
        this.datas = request;
        console.log(this.datas)

        this.issue_count = request.count;
        this.effort_total = request.effortTotal;
        const bugs = request.bugs;
        for (const temp of bugs){
          this.types.push(temp.type);
          this.titles.push(temp.title);
          this.severities.push(temp.severity);
          this.efforts.push(temp.effort);
          this.components.push(temp.component);
          this.redirectUrls.push(temp.redirectUrl);
        }
      }
    );
  }

}
