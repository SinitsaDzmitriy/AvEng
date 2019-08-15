import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SampleListComponent} from './sample-list/sample-list.component';
import {PlayBoardComponent} from './play-board/play-board.component';
const routes: Routes = [
  { path: 'samples', component: SampleListComponent },
  { path: 'play', component: PlayBoardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
