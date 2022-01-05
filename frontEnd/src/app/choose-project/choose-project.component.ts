import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {GetProjectInfoService} from './get-project-info.service';
import {DeleteProjectService} from './delete-project.service';
import {GetRepoInfoOfChosenProjectService} from "../choose-repository/get-repo-info-of-chosen-project.service";

@Component({
  selector: 'app-choose-project',
  templateUrl: './choose-project.component.html',
  styleUrls: ['./choose-project.component.css']
})
export class ChooseProjectComponent implements OnInit {
  projectNames = new Array();
  projectIntroduction = new Array();
  projectRepoNumbers = new Array();
  ProjectStartTime = new Array();
  datas: any;
  responsedata: any;
  item:any;
  totalProject:any;
  UserID = '';
  ChosenProjectID = '';

  ProjectID = '';
  repo_datas: any;
  owner = new Array();
  repoNames = new Array();
  totalData = new Array();

  constructor(private router: Router, private getrepoinfoofchosenproject: GetRepoInfoOfChosenProjectService, private getProjectInfoService: GetProjectInfoService, private delProjectService: DeleteProjectService, private activerouter:ActivatedRoute ) {}

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.UserID = window.sessionStorage.getItem('UserID');
    this.getTotalProjectInfo();
  }

   getTotalProjectInfo() {
      const UserProjectData = {
        userId:undefined,
      };
      UserProjectData.userId  = this.UserID;
      const data = JSON.stringify(UserProjectData);
      this.getProjectInfoService.getUserProjectData(data).subscribe(
        request => {
          this.datas = request;
          console.log(this.datas);
        }
      );
  }

  choose_repo(projectid, projectName, projectDescription) {
    console.log(projectid);
    // const chosenId: string = event.target.id.toString();
    sessionStorage.setItem('ChosenProjectID', projectid);
    sessionStorage.setItem('projectName', projectName);
    sessionStorage.setItem('projectIntroduction', projectDescription);
    console.log("chosenid:",projectid)

    this.ProjectID = window.sessionStorage.getItem('ChosenProjectID');

    const UserRepoData = {
      projectId:undefined,
    };
    UserRepoData.projectId  = this.ProjectID;
    const data = JSON.stringify(UserRepoData);
    this.getrepoinfoofchosenproject.getRepoDataOfProject(data).subscribe(
      request => {
        this.repo_datas = request;
        console.log(this.repo_datas);
        for(let item of this.repo_datas){
          this.repoNames.push(item.repoName);
          this.owner.push(item.ownerName);

          console.log(this.repoNames);
          console.log(this.owner);

          sessionStorage.setItem('repoName', this.repoNames[0]);
          sessionStorage.setItem('owner', this.owner[0]);
        }
        this.router.navigate(['project-analysis']);
        // this.router.navigate(['issue-track']);

      }
    );

    // this.router.navigate(['choose-repository']);
    // this.router.navigate(['sonarqube']);

  }


  delete_repo(delId) {
    const deletedprojectId: string = delId.toString();
    console.log("choose to delete id:",deletedprojectId);
    if(confirm("確認要刪除此專案嗎?"))
    {
      const DeleteProject = {
            userId:undefined,
            projectId:undefined
          };
      DeleteProject.userId  = this.UserID;
      DeleteProject.projectId = deletedprojectId;
      const deldata = JSON.stringify(DeleteProject);
      this.delProjectService.deleteChosenProject(DeleteProject).subscribe(
        request => {
        this.responsedata = request;
        console.log(this.responsedata);
          if(this.responsedata.isSuccess == "true"){
            alert("刪除專案成功")
            //this.router.navigate(['choose-project']);
          }
          else{
            alert("刪除專案失敗")
          }

        }
      );
    }else{
      //this.router.navigate(['choose-project']);

    }
    window.location.reload();

  }

  // tslint:disable-next-line:typedef
  goToAddProjectPage() {
    this.router.navigate(['createproject']);
  }



}
