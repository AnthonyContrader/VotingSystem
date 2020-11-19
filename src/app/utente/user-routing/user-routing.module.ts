import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserDashboardComponent } from '../user-dashboard/user-dashboard.component';
import { UserLayoutComponent } from '../../layout/user-layout/user-layout.component';
import { SchedeListComponent } from '../schede-list/schede-list.component';
import { VotazioneComponent } from '../votazione/votazione.component';
import { UserProfiloComponent } from '../user-profilo/user-profilo.component';

const routes: Routes = [
  { path: 'user-dashboard', component: UserLayoutComponent, children: [
    {path: '', component: UserDashboardComponent},
    {path: 'schede-list', component: SchedeListComponent},
    {path: 'votazione', component: VotazioneComponent},
    {path: 'user-profilo', component: UserProfiloComponent}
  ]}
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
