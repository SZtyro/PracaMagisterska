import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LearningComponent } from './components/learning/learning.component';
import { PageAComponent } from './components/page-a/page-a.component';
import { PageBComponent } from './components/page-b/page-b.component';
import { TestsComponent } from './components/tests/tests.component';
import {InfoComponent} from "./components/info/info.component";

const routes: Routes = [
  {
    path: 'pageA', component: PageAComponent
  },
  {
    path: 'pageB', component: PageBComponent
  },
  {
    path: 'learning', component: LearningComponent
  },
  {
    path: 'tests', component: TestsComponent
  },
  {
    path: 'home', component: InfoComponent
  },
  {
    path: '**', redirectTo: 'home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
