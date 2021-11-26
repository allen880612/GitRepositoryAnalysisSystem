import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-project-choose',
  templateUrl: './add-project-choose.component.html',
  styleUrls: ['./add-project-choose.component.css']
})
export class AddProjectChooseComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToCreateSonarbPage() {
    console.log('Sonar');
    this.router.navigate(['createproject', 'sonar']);
  }

  goToCreateGithubPage() {
    console.log('Github');
    this.router.navigateByUrl('createproject/github');
  }
}
