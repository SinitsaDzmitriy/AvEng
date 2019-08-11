import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SampleListComponent } from './sample-list/sample-list.component';
import { PlayBoardComponent } from './play-board/play-board.component';

import {SampleService} from "./services/sample.service";

@NgModule({
  declarations: [
    AppComponent,
    SampleListComponent,
    PlayBoardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [SampleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
