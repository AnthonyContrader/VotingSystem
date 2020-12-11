import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SchedeListaComponent } from './schede-lista/schede-lista.component';
import { VotingsystemFrontendSharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { SchedaVotoComponent } from './scheda-voto/scheda-voto.component';



@NgModule({
  declarations: [
    SchedeListaComponent,
    SchedaVotoComponent
  ],
  imports: [
    CommonModule,
    VotingsystemFrontendSharedModule,
    RouterModule.forChild([
      {
        path: 'schede-lista',
        component: SchedeListaComponent
      },
      {
        path: 'scheda-voto',
        component: SchedaVotoComponent
      },
    ])

  ]
})
export class UserModule { }
