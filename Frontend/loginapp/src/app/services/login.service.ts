import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = "http://localhost:8080"; 

  constructor(private http:HttpClient) { }


  // calling to generate server

  generateToken(credentials:any){
    // token generate
    return this.http.post(`${this.url}/token`,credentials)

  }
  


  // for login user

  loginUser(token: string){
    localStorage.setItem("token",token);
    return true;
  }

  // is looged in or not
  isLoggedIn(){
    let token=localStorage.getItem("token");
    if(token==undefined || token==='' || token==null){
      return false;
    }
    return true;
  }

  // logout
  logout(){
    localStorage.removeItem("token");
    return true;
  }

  getToken(){
    return localStorage.getItem("token");
  }


}
