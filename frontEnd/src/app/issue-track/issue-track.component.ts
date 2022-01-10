import {Component, OnInit} from '@angular/core';
import {IssueTrackService} from './issue-track.service';
import {Router, ActivatedRoute} from '@angular/router';
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'app-issue-track',
  templateUrl: './issue-track.component.html',
  styleUrls: ['./issue-track.component.css']
})

export class IssueTrackComponent implements OnInit {
  datas: any;
  labels = [];
  titles = [];
  posters = [];
  posterIds = [];
  bodys = [];
  startDates = [];
  updateDates = [];
  closeDates = [];
  States = [];
  avatars = [];
  StatusColor = [];
  html_urls = [];
  owner: any;
  repo: any;
  options: string[] = ['ByLabel'];

  slideToggleColor: ThemePalette = 'accent';
  slideToggleChecked = true;
  toggleState: string = "open";

  constructor(private router: Router, private issueTrackService: IssueTrackService, private acrouter: ActivatedRoute) {}

  ngOnInit(): void {
    this.repo = window.sessionStorage.getItem('repoName');
    this.owner = window.sessionStorage.getItem('owner');

    console.log(this.repo);
    console.log(this.owner);

    this.getIssueTrack();
  }

  getIssueTrack() {
    const issueTrackData = {
      owner: undefined,
      repo: undefined
    };
    issueTrackData.owner = this.owner;
    issueTrackData.repo = this.repo;
    const data = JSON.stringify(issueTrackData);
    this.issueTrackService.getIssueTrackService(data).subscribe(
      request => {
        this.datas = request;
        console.log((this.datas));
        for (const temp of this.datas){
          this.titles.push(temp.title);
          this.bodys.push(temp.body);
          this.States.push(temp.state);
          this.avatars.push(temp.avatar);
          if (temp.state === 'open') { this.StatusColor.push('red'); }
          else { this.StatusColor.push('gray'); }
          this.labels.push(temp.labels);
          this.posters.push(temp.issuePoster);
          this.startDates.push(temp.created_at);
          this.updateDates.push(temp.updated_at);
          this.closeDates.push(temp.closed_at);
          this.posterIds.push(temp.issuePosterId);
          this.html_urls.push(temp.html_url);
        }
      }
    );
  }

  changed() {
    if (this.slideToggleChecked) {
      this.toggleState = "open";
    }
    else {
      this.toggleState = "closed";
    }
  }
}
