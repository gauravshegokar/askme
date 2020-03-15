import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from '@app/_services/authentication.service'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router,
    private authenticationService: AuthenticationService
    ) { }
  
  isLoggedIn = false
  currentUser = this.authenticationService.currentUser.subscribe(
    (user) => {
      if(user){
        this.isLoggedIn = true
      }else{
        this.isLoggedIn = false
      }
    },
    (msg) => {
      console.log('Error Getting Location: ', msg);
    }
  );

  ngOnInit(): void {
  }

  navigateToLogin() {
    this.router.navigate(['login'])
  }

  navigateToSignup() {
    this.router.navigate(['signup'])
  }

  logout(){
    this.authenticationService.logout()
  }

  profile(){

  }

  createPost(){
    
  }
}
