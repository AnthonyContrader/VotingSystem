import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SchedeComponent } from '../schede.component';
import { RouterModule } from '@angular/router';
import { schedeRoute } from '../schede.route';
import { SchedaStatisticaComponent } from '../scheda-statistica/scheda-statistica.component';
import { SchedaLetturaComponent } from '../scheda-lettura/scheda-lettura.component';
import { VotingsystemFrontendSharedModule } from '../../../shared/shared.module';
import { SchedaEditComponent } from '../../schede/scheda-edit/scheda-edit.component';





@NgModule({
  declarations: [
    SchedeComponent,
    SchedaLetturaComponent,
    SchedaStatisticaComponent,
    SchedaEditComponent
  ],
  imports: [
    VotingsystemFrontendSharedModule, 
    CommonModule,
    RouterModule.forChild(schedeRoute)
  ]
})
export class SchedeManagementModule { }
