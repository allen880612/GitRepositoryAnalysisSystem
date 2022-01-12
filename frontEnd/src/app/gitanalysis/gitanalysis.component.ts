import { Component, OnInit } from '@angular/core';
import {CommitTrendService} from "../commit-trend/commit-trend.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-gitanalysis',
  templateUrl: './gitanalysis.component.html',
  styleUrls: ['./gitanalysis.component.css']
})
export class GitanalysisComponent implements OnInit {

  constructor(private commitTrendService: CommitTrendService, private acrouter: ActivatedRoute) { }//目前先放commit-trend

  // 畫圖
  datas: any;
  commitList: any;
  barChartOptions = {
    responsive: true
  };
  barChartType = 'line';
  barChartLegend = true;

  barChartLabels = [];
  barChartData = [
    {data: [], label: 'Commit Trend'},
    {data: [], label: 'Additions'},
    {data: [], label: 'Deletions'},
    // {data: [], label: 'CodeBase'}
  ];

  //codebase圖

  barCodeBaseChartData = [
    {data: [], label: 'CodeBase'}
  ];
  barCodeBaseChartLabels = [];
  barCodeBaseChartOptions = {
    responsive: true
  };
  barCodeBaseChartLegend = true;
  barCodeBaseChartType = 'line';


  // 個人圖

  barChartOptions2 = {
    responsive: true
  };
  barChartType2 = 'line';
  barChartLegend2 = true;

  barChartDataIn = [[]];
  // tslint:disable-next-line:max-line-length
  tatolbarCharlist = [];
  leftTatolbarCharlist = [];
  rightTatolbarCharlist = [];

  commitTitleList = [];
  codebaseTitleList = [];

  commitCounts: any;
  owner: any;
  repo: any;
  projectName: any;
  projectIntroduction: any;
//控制canvas
  total_commit_canvas:boolean;
  contributor_commit_canvas:boolean;
  codebase_canvas:boolean;

  githubUrl: string;

  IssueTrackButton: string = "";
  ContributorButton: string = "";
  CodeBaseButton: string = "";
  CommitButton: string = "";
  IssueListButton: string = "";
  RepoInfoButton: string = "";

  // tslint:disable-next-line:typedef
  ngOnInit(): void {
    this.repo = window.sessionStorage.getItem('repoName');
    this.owner = window.sessionStorage.getItem('owner');
    this.projectName = window.sessionStorage.getItem('projectName');
    this.projectIntroduction = window.sessionStorage.getItem('projectIntroduction');

    this.total_commit_canvas=false;
    this.contributor_commit_canvas=false;
    console.log(this.repo)
    console.log(this.owner)

    this.githubUrl = "http://github.com/" + this.owner + "/" + this.repo
    console.log(this.githubUrl);

    this.getRepoInfo();
  }

  getContributor(){
    document.getElementById('codebase_canvas').style.display="none"
    document.getElementById('total_canvas').style.display="none"
    document.getElementById('issue_track').style.display="none"
    document.getElementById('contributor_canvas').style.display=""
    document.getElementById('bug_list').style.display="none"
    document.getElementById('repo_info').style.display="none"
    this.IssueTrackButton = "";
    this.ContributorButton = "TabButton";
    this.CodeBaseButton = "";
    this.CommitButton = "";
    this.IssueListButton = "";
    this.RepoInfoButton = "";

    if(!this.contributor_commit_canvas){
      const commitData = {
        owner: undefined,
        repo: undefined
      };
      commitData.owner = this.owner;
      commitData.repo = this.repo;
      const data = JSON.stringify(commitData);
      this.commitTrendService.getCommit(data).subscribe(
      request => {
          this.datas = request;
          console.log(this.datas);
        //個別圖
          for (let i = 1; i < this.datas.length; i++) {
            for (const temp of this.datas[i].weeks_stats) {
              this.barChartDataIn.push([]);
              this.barChartDataIn[i].push(+temp.commits.toString());
            }
          }

          for (let i = 1; i < this.datas.length; i++) {
            let temp: any[];
            temp = [];
            const barChartLabels2 = [];
            const barChartData2 = [
              {data: [], label: 'Commit Trend'},
              {data: [], label: 'Additions'},
              {data: [], label: 'Deletions'}
            ];

            temp.push(this.barChartOptions2);
            temp.push(this.barChartType2);
            temp.push(this.barChartLegend2);

            for (const temp of this.datas[i].weeks_stats) {
              const s = new Date(+temp.start_week * 1000);
              // clear?
              barChartLabels2.push(s.toLocaleDateString());
              barChartData2[0].data.push(temp.commits);
              barChartData2[1].data.push(temp.additions);
              barChartData2[2].data.push(temp.deletions);
            }
            temp.push(barChartLabels2);
            temp.push(barChartData2);
            temp.push(this.datas[i].user_name);
            temp.push(this.datas[i].total_commits);
            temp.push(this.datas[i].total_additions);
            temp.push(this.datas[i].total_deletions);
            temp.push("https://github.com/" + this.datas[i].user_name);

            this.tatolbarCharlist.push(temp);
          }

          for (let i = 0; i < Math.round(this.tatolbarCharlist.length / 2); i++) {
            this.leftTatolbarCharlist.push(this.tatolbarCharlist[i]);
          }
          for (let i = Math.round(this.tatolbarCharlist.length / 2); i < this.tatolbarCharlist.length; i++) {
            this.rightTatolbarCharlist.push(this.tatolbarCharlist[i]);
          }
          this.contributor_commit_canvas=true;
        }
      );
    }
  }

  getCommitTrend() {
    document.getElementById('codebase_canvas').style.display="none"
    document.getElementById('contributor_canvas').style.display="none"
    document.getElementById('issue_track').style.display="none"
    document.getElementById('total_canvas').style.display=""
    document.getElementById('bug_list').style.display="none"
    document.getElementById('repo_info').style.display="none"
    this.IssueTrackButton = "";
    this.ContributorButton = "";
    this.CodeBaseButton = "";
    this.CommitButton = "TabButton";
    this.IssueListButton = "";
    this.RepoInfoButton = "";

    if(!this.total_commit_canvas){
      const commitData = {
        owner: undefined,
        repo: undefined
      };

      commitData.owner = this.owner;
      commitData.repo = this.repo;
      const data = JSON.stringify(commitData);
      this.commitTrendService.getCommit(data).subscribe(
      request => {
          this.datas = request;
          // all 圖
          for (const temp of this.datas[0].weeks_stats) {
            const s = new Date(+temp.start_week * 1000);
            // clear?
            this.barChartLabels.push(s.toLocaleDateString());
            this.barChartData[0].data.push(+temp.commits.toString());
            this.barChartData[1].data.push(+temp.additions.toString());
            this.barChartData[2].data.push(+temp.deletions.toString());
            // this.barChartData[3].data.push(+temp.lines_count.toString());
          }
          this.commitCounts = this.datas[0].total_commits;
          this.total_commit_canvas=true;

          this.commitTitleList.push(this.datas[0].total_commits);
          this.commitTitleList.push(this.datas[0].total_additions);
          this.commitTitleList.push(this.datas[0].total_deletions);
        }
      );
    }
  }

  getCodeBase(){
    document.getElementById('total_canvas').style.display="none"
    document.getElementById('issue_track').style.display="none"
    document.getElementById('contributor_canvas').style.display="none"
    document.getElementById('codebase_canvas').style.display=""
    document.getElementById('bug_list').style.display="none"
    document.getElementById('repo_info').style.display="none"
    this.IssueTrackButton = "";
    this.ContributorButton = "";
    this.CodeBaseButton = "TabButton";
    this.CommitButton = "";
    this.IssueListButton = "";
    this.RepoInfoButton = "";

    if(!this.codebase_canvas){
      const commitData = {
       owner: undefined,
       repo: undefined
      };
      commitData.owner = this.owner;
      commitData.repo = this.repo;
      const data = JSON.stringify(commitData);
      this.commitTrendService.getCommit(data).subscribe(
        request => {
          this.datas = request;
          // all 圖
          for (const temp of this.datas[0].weeks_stats) {
            const s = new Date(+temp.start_week * 1000);
            // clear?
            this.barCodeBaseChartLabels.push(s.toLocaleDateString());
            this.barCodeBaseChartData[0].data.push(+temp.lines_count.toString());
          }
          this.codebase_canvas=true;
          this.codebaseTitleList.push(this.datas[0].lines_count);
        }
      );
    }
  }

  getIssueTrack() {
    document.getElementById('codebase_canvas').style.display="none"
    document.getElementById('contributor_canvas').style.display="none"
    document.getElementById('total_canvas').style.display="none"
    document.getElementById('issue_track').style.display=""
    document.getElementById('bug_list').style.display="none"
    document.getElementById('repo_info').style.display="none"
    this.IssueTrackButton = "TabButton";
    this.ContributorButton = "";
    this.CodeBaseButton = "";
    this.CommitButton = "";
    this.IssueListButton = "";
    this.RepoInfoButton = "";
  }

  getBugList() {
    document.getElementById('codebase_canvas').style.display="none"
    document.getElementById('contributor_canvas').style.display="none"
    document.getElementById('total_canvas').style.display="none"
    document.getElementById('issue_track').style.display="none"
    document.getElementById('bug_list').style.display=""
    document.getElementById('repo_info').style.display="none"
    this.IssueTrackButton = "";
    this.ContributorButton = "";
    this.CodeBaseButton = "";
    this.CommitButton = "";
    this.IssueListButton = "TabButton";
    this.RepoInfoButton = "";
  }

  getRepoInfo() {
    document.getElementById('codebase_canvas').style.display="none"
    document.getElementById('contributor_canvas').style.display="none"
    document.getElementById('total_canvas').style.display="none"
    document.getElementById('issue_track').style.display="none"
    document.getElementById('bug_list').style.display="none"
    document.getElementById('repo_info').style.display=""
    this.IssueTrackButton = "";
    this.ContributorButton = "";
    this.CodeBaseButton = "";
    this.CommitButton = "";
    this.IssueListButton = "";
    this.RepoInfoButton = "TabButton";
  }
}
