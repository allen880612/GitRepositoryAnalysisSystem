import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AuthorizeService {

  constructor(private httpClient: HttpClient) { }

  public getAccessToken(body: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'text/json'
    });
    const options = {
      headers
    };
    console.log(options);
    return this.httpClient.post<any>('/GitRepositoryAnalysisSystem/authorizeGithub', body, options);
  }
}
