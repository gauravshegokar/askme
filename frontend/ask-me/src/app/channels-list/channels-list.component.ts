import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {ChannelsListService} from "@app/channels-list/channels-list.service";
import { Channels } from "@app/_models/channels";
import { Router } from '@angular/router';

@Component({
  selector: 'app-channels-list',
  templateUrl: './channels-list.component.html',
  styleUrls: ['./channels-list.component.scss']
})
export class ChannelsListComponent implements OnInit {

  form: FormGroup = new FormGroup({
    selChannel: new FormControl(''),
    selAction: new FormControl('')
  });

  error: string | null
  constructor(private channelsListService: ChannelsListService,
    private router: Router) { }

  public channelsData: Channels

  ngOnInit(): void {
    this.loadChannels()
  }

  loadChannels() {
    this.channelsListService.getChannels().subscribe(
      response => {
        this.channelsData = response
      },
      err => {
        console.log(err)
      }
    )
  }

  submit() {
    this.channelsListService.subscribeChannel(this.form.value.selChannel,this.form.value.selAction).subscribe(
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
