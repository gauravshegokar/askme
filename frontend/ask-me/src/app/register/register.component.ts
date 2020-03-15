import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RegisterService } from './register.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    fname: new FormControl(''),
    lname: new FormControl(''),

  });

  error: string | null;

  constructor(private registerService: RegisterService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  register() {
    this.registerService.register().subscribe(
      response => {
        console.log(response.status)
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
