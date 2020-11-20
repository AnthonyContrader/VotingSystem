import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from '../layout/admin-layout/admin-layout.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { VotantiListaComponent } from './votanti-lista/votanti-lista.component'
import { SchedeComponent } from './schede/schede.component';
import { SchedaLetturaComponent } from './schede/scheda-lettura/scheda-lettura.component';
import { SchedaStatisticaComponent } from './schede/scheda-statistica/scheda-statistica.component';

/**
 * Modulo di routing dell'admin. Qui ci sono i percorsi che un admin può seguire:
 * appena fa il login viene caricato nel <router-outlet> di app-component il layout e nel 
 * <router-outlet> del layout (come percorsi "children") vengono visualizzati gli altri 
 * (qui sotto sono indentati).
 * 
 * @author Vittorio Valent
 * 
 * @see AdminLayoutComponent
 * 
 * @see layout
 */
const routes: Routes = [
  { path: 'admin-dashboard', component: AdminLayoutComponent, children:[
    { path: '', component: AdminDashboardComponent},
    { path: 'users', component: UsersComponent},
    { path: 'work-in-progress', component: WorkInProgressComponent},
    { path: 'votanti-lista', component: VotantiListaComponent},
    { path: 'schede', component: SchedeComponent},
    { path: 'schede/scheda-lettura', component: SchedaLetturaComponent},
    { path: 'schede/scheda-statistica', component: SchedaStatisticaComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }