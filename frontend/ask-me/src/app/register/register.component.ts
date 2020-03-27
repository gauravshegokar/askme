import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RegisterService } from './register.service'
import { Router } from '@angular/router';
import { Caretaker, Originator } from './register-memento'
import { ClonerService } from '../_services/cloner.service'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  submitted = false;
  form: FormGroup;

  private originator: Originator
  private caretaker: Caretaker

  error: string | null;

  constructor(private registerService: RegisterService,
    private router: Router,
    private clonerService: ClonerService
  ) {
  }

  ngOnInit(): void {
    this.initialize()
  }

  initialize() {

    // saving 
    this.form = this.getFormInstance()

    this.caretaker = new Caretaker();
    this.originator = new Originator();

    this.originator.setState(this.clonerService.deepClone(this.form))
    this.caretaker.addMemento(this.originator.commit())

  }

  getFormInstance() {
    return new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(4)]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      fname: new FormControl(''),
      lname: new FormControl(''),
      userType: new FormControl('regular'),
    });
  }

  resetForm() {
    console.log("resetting the form")
    this.form = this.clonerService.deepClone(this.caretaker.getMemento(0).getState())
  }

  saveForm() {
    console.log("saving the form")
    // let currentSnapshot = Object.assign({}, this.form)
    let currentSnapshot = this.clonerService.deepClone(this.form)
    this.originator.setState(currentSnapshot)
    this.caretaker.addMemento(this.originator.commit())
  }

  undoForm() {
    console.log("undo the form")
    this.form = this.clonerService.deepClone(this.caretaker.getMemento(-1).getState())
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
