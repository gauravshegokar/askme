import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthenticationService } from '@app/_services/authentication.service';
import { first } from 'rxjs/operators';
import { Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  error: string | null;
  op: null;
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
  }

  submit() {
    console.log(this.form.value.username)
    console.log(this.form.value.password)

    this.authenticationService.login(this.form.value.username, this.form.value.password)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data)
          this.router.navigate(['/']);
        },
        error => {
          console.log(error)
          this.error = error;
          // this.loading = false;
        });
  }
}
