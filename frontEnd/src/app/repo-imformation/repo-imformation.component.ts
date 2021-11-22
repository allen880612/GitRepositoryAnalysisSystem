import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-repo-imformation',
  templateUrl: './repo-imformation.component.html',
  styleUrls: ['./repo-imformation.component.css']
})
export class RepoImformationComponent implements OnInit {
  owner: any;
  repoName = 'Repo1';
  redirecturl: string;


  constructor(private router: Router, private activerouter: ActivatedRoute) {
  }

  ngOnInit(): void {
    //console.log('repo');
    // console.log(this.router.url.toString());
    let current_url = this.router.url.toString();
    if (current_url == '/code-base') {
      this.redirecturl = 'analysis';
    } else if (current_url == '/analysis') {
      this.redirecturl = 'choose-project';
    }

    this.repoName = window.sessionStorage.getItem('repoName');
    this.owner = window.sessionStorage.getItem('owner');
  }

  NavitoAnalysis() {
    // console.log( 'r' + this.redirecturl);
    this.router.navigateByUrl(this.redirecturl);
  }
}
