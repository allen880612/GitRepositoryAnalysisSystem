import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BuglistService} from "./buglist.service";
import {ThemePalette} from "@angular/material/core";

class JSONObject {}

export  interface Task {
  name: string;
  completed: boolean;
  color: ThemePalette;
  subtasks?: Task[];
}

export  interface SeverityTask {
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

  allComplete: boolean = true;
  task: Task = {
    name: 'Select ALL',
    completed: true,
    color: 'primary',
    subtasks: [
      {name: 'BUG', completed: true, color: 'accent'},
      {name: 'CODE_SMELL', completed: true, color: 'warn'},
      {name: 'VULNERABILITY', completed: true, color: 'primary'},
    ],
  };

  severityAllComplete: boolean = true;
  severityTask: SeverityTask = {
    name: 'Select ALL',
    completed: true,
    color: 'primary',
    subtasks: [
      {name: 'BLOCKER', completed: true, color: 'accent'},
      {name: 'CRITICAL', completed: true, color: 'accent'},
      {name: 'MAJOR', completed: true, color: 'warn'},
      {name: 'MINOR', completed: true, color: 'warn'},
      {name: 'INFO', completed: true, color: 'primary'},
    ],
  };

  constructor(private router: Router, private BuglistService: BuglistService, private acrouter: ActivatedRoute) {}

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

  severityUpdateAllComplete() {
    this.severityAllComplete = this.severityTask.subtasks != null && this.severityTask.subtasks.every(t => t.completed);
  }

  severitySomeComplete(): boolean {
    if (this.severityTask.subtasks == null) {
      return false;
    }
    return this.severityTask.subtasks.filter(t => t.completed).length > 0 && !this.severityAllComplete;
  }

  severitySetAll(completed: boolean) {
    this.severityAllComplete = completed;
    if (this.severityTask.subtasks == null) {
      return;
    }
    this.severityTask.subtasks.forEach(t => (t.completed = completed));
  }

  getBugList() {
    // this.types.push("BUG");
    // this.types.push("CODE_SMELL");
    // this.types.push("VULNERABILITY");
    //
    // this.titles.push("abcdefghijklmnopqrstuvwxyz");
    // this.titles.push("bug2");
    // this.titles.push("bug3");
    //
    // this.severities.push("AAA");
    // this.severities.push("BBB");
    // this.severities.push("CCC");
    //
    // this.efforts.push("10min");
    // this.efforts.push("20min");
    // this.efforts.push("30min");
    //
    // this.components.push("component1");
    // this.components.push("component2");
    // this.components.push("component3");
    //
    // this.redirectUrls.push("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    // this.redirectUrls.push("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    // this.redirectUrls.push("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
    //
    // this.issue_count = "6789";
    // this.effort_total = "297";

    const bugListData = {
      projectId: this.ProjectID,
    };
    const data = JSON.stringify(bugListData);
    this.BuglistService.getBugListService(data).subscribe(
      request => {
        this.datas = request;
        console.log(this.datas);

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
