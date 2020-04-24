import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '@app/_services/authentication.service'

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
  public profileId: string

  currentUser = this.authenticationService.currentUser.subscribe(
    (user) => {
      if (user) {
        this.isLoggedIn = true
      } else {
        this.isLoggedIn = false
      }
    },
    (msg) => {
      console.log('Error ', msg);
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

  logout() {
    this.authenticationService.logout()
  }

  profile() {
    this.profileId = this.authenticationService.currentUserValue.id
    this.router.navigate(['profile'], { queryParams: { profileId: this.profileId } })
  }

  createPost() {
    this.router.navigate(['newPost'])
  }

  createChannel() {
    this.router.navigate(['newChannel'])
  }

  channelsList() {
    this.router.navigate(['channelsList'])
  }

  createSearch() {
    this.router.navigate(['searchPath'])
  }
}
