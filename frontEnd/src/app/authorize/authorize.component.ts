import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AuthorizeService} from "./authorize.service";

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['./authorize.component.css']
})
export class AuthorizeComponent implements OnInit {

  constructor(private acRouter: ActivatedRoute, private  authorizeService: AuthorizeService) { }

  ngOnInit(): void {
    const grant = this.acRouter.snapshot.paramMap.get('code');
    const callBackData = {
      code: grant,
    };

    const data = JSON.stringify(callBackData);
    this.authorizeService.getAccessToken(data).subscribe(
      request => {
        console.log('post response =', request.toString());
        const token = request.accessToken.toString();
        alert(request);
        console.log('token =', token);
        alert(token);
        this.redirectTo('homepage');
        sessionStorage.setItem('Username', 'githubOAuth');
        sessionStorage.setItem('UserID', 'githubOAuth');
        sessionStorage.setItem('Token', token);
      });
  }
}
