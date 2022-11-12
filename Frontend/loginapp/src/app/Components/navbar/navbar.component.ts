import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public loggedIn=false;
  public userdetials:any

  constructor(private loginService:LoginService,private user:UserService) { }

  ngOnInit(): void {
    this.loggedIn=this.loginService.isLoggedIn();
    this.user.getUser().subscribe(
      user=>{
        this.userdetials=user
      },
      error=>{
        console.log(error);
        
      }
    )
  }

  logoutUser(){
    this.loginService.logout();
    location.reload()
  }

}
