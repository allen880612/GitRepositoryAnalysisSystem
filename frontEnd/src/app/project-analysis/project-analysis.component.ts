import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-analysis',
  templateUrl: './project-analysis.component.html',
  styleUrls: ['./project-analysis.component.css']
})
export class ProjectAnalysisComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    window.scrollTo(0, 0);
  }

}
