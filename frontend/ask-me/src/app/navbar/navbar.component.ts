import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '@app/_services/authentication.service'

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router,
    private authenticationService: AuthenticationService,
              private activatedRoute: ActivatedRoute
    ) { }

  isLoggedIn = false
  public profileId : string

  currentUser = this.authenticationService.currentUser.subscribe(
    (user) => {
      if(user){
        this.isLoggedIn = true
        this.profileId = user.id
      }else{
        this.isLoggedIn = false
        this.profileId = null
      }
    },
    (msg) => {
      console.log('Error Getting Location: ', msg);
    }
  );

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.profileId = params['profileId'];
    });
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
    this.router.navigate(['profile'],{ queryParams: { profileId : this.profileId } })
  }

  createPost(){
    this.router.navigate(['newPost'])
  }

  createSearch(){
    this.router.navigate(['searchPath'])
  }
}
