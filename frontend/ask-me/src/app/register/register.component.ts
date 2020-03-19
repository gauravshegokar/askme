import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RegisterService } from './register.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  submitted = false;
  form: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(4)]),
    password: new FormControl('', [Validators.required, Validators.minLength(4)]),
    fname: new FormControl(''),
    lname: new FormControl(''),
    userType: new FormControl('regular'),
  });

  error: string | null;

  constructor(private registerService: RegisterService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  register() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }
    this.registerService.register(this.form.value).subscribe(
      response => {
        if (response.status == 201) {
          this.router.navigate(['/login']);
        } else {
          this.error = "Something went wrong"
        }
      },
      err => {
        this.error = err.error.error
        console.log(err)
      }
    )
  }
}
