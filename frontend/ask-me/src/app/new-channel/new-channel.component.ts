import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { NewChannelService } from "@app/new-channel/new-channel.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-channel',
  templateUrl: './new-channel.component.html',
  styleUrls: ['./new-channel.component.scss']
})
export class NewChannelComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selChannelName: new FormControl(''),
    selChannelDesc: new FormControl('')
  });

  error: string | null
  constructor(private newChannelService: NewChannelService,
    private router: Router) { }

  ngOnInit(): void {
  }

  submit() {
    this.newChannelService.newChannelPublish(this.form.value.selChannelName, this.form.value.selChannelDesc).subscribe(
      response => {
        if (response.status == 201 || response.status == 200) {
          this.router.navigate(['/']);
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
