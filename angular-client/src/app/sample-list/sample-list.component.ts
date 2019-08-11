import { Component, OnInit } from '@angular/core';
import {Sample} from "../model/sample";
import {SampleService} from "../services/sample.service";

@Component({
  selector: 'app-sample-list',
  templateUrl: './sample-list.component.html',
  styleUrls: ['./sample-list.component.css']
})
export class SampleListComponent implements OnInit {

  samples: Sample[];
  showDeleteMessage = false;

  private service: SampleService;

  constructor(service: SampleService) {
    this.service = service;
  }

  ngOnInit() {
    this.refesh();
  }

/*  deleteUser(id: number) {
    this.service.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.showDeleteMessage = true;
          this.service.listAll().subscribe(data => {
            this.samples = data;
          })
        },
        error => console.log(error));
  }*/
  refesh () {
    this.service.listAll().subscribe(data => {
      this.samples = data;
    });
  }


  view(id: number) {
    window.alert(id);
  }

}
