import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthorizeService} from './authorize.service';

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['./authorize.component.css']
})
export class AuthorizeComponent implements OnInit {

  constructor(private router: Router, private acRouter: ActivatedRoute, private  authorizeService: AuthorizeService) { }

  ngOnInit(): void {
    this.acRouter.queryParams
      .subscribe(params => {
          console.log(params);
          const grant = params.code;
          this.authorize(grant);
        }
      );
  }

  authorize(grant): void {
    const callBackData = {
      code: grant,
    };

    const data = JSON.stringify(callBackData);
    this.authorizeService.getAccessToken(data).subscribe(
      request => {
        console.log(request.toString());

        console.log(request);
        sessionStorage.setItem('Username', request.name);
        sessionStorage.setItem('UserID', request.id);
        sessionStorage.setItem('avatarUrl', request.avatarUrl);
        console.log(request.avatarUrl);
        this.router.navigateByUrl('choose-project');
      }
    );
  }
}
