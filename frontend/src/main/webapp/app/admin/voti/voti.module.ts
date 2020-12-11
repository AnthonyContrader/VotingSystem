import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VotiListaComponent } from './voti-lista/voti-lista.component';
import { VotingsystemFrontendSharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { FilteridPipe } from '../../filterid.pipe';



@NgModule({
  declarations: [
    VotiListaComponent,
    FilteridPipe
  ],
  imports: [
    VotingsystemFrontendSharedModule,
    CommonModule,
    RouterModule.forChild([
      {
        path: '',
        component: VotiListaComponent
      }
  ]),

  ],
  exports: [
    FilteridPipe
  ]
})
export class VotiModule { }
