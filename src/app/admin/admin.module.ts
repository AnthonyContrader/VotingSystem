import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UsersComponent } from './users/users.component';
import { WorkInProgressComponent } from './work-in-progress/work-in-progress.component';
import { SchedeComponent } from './schede/schede.component';
import { VotantiListaComponent } from './votanti-lista/votanti-lista.component';
import { SchedaLetturaComponent } from './schede/scheda-lettura/scheda-lettura.component';
import { SchedaStatisticaComponent } from './schede/scheda-statistica/scheda-statistica.component';

/**
 * Modulo dell'admin, qui vengono dichiarate le component che utilizza 
 * l'admin. Questo modulo importa AdminRoutingModule.
 * 
 * @author Vittorio Valent
 * 
 * @see AdminRoutingModule
 */
@NgModule({
  declarations: [AdminDashboardComponent, UsersComponent, WorkInProgressComponent, SchedeComponent, VotantiListaComponent, SchedaLetturaComponent, SchedaStatisticaComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ]
})
export class AdminModule { }
