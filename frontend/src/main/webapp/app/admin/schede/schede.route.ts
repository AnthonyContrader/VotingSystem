import { Routes } from '@angular/router';
import { SchedaLetturaComponent } from './scheda-lettura/scheda-lettura.component';
import { SchedeComponent } from './schede.component';
import { SchedaStatisticaComponent } from './scheda-statistica/scheda-statistica.component';
import { SchedaEditComponent } from '../schede/scheda-edit/scheda-edit.component';

export const schedeRoute: Routes = [
    {
        path: '',
        component: SchedeComponent,
        data: {
            defaultSort: 'id,asc',
        }
    },
    {
        path: 'schede/scheda-lettura',
        component: SchedaLetturaComponent
    },
    {
        path: 'schede/scheda-statistica',
        component: SchedaStatisticaComponent,
        data: {
            defaultSort: 'id,asc',
        }
    },
    {
        path: 'schede/scheda-edit',
        component: SchedaEditComponent
    },

];