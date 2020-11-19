import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginModule } from './login/login.module';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from './layout/layout.module';
import { AdminModule } from './admin/admin.module';
import { UserModule } from './utente/user/user.module';
import { SchedeListComponent } from './utente/schede-list/schede-list.component';
import { SchedaComponent } from './scheda/scheda.component';
import { UtenteVotanteComponent } from './utente-votante/utente-votante.component';
import { VotazioneComponent } from './utente/votazione/votazione.component';
import { UserProfiloComponent } from './utente/user-profilo/user-profilo.component';

/** 
 * Modulo principale dell'applicazione. Qui vengono importati i moduli secondari. L'UNICA component
 * da dichiare qui è l'AppComponent, tutte le altre devono essere dichiarate nel loro modulo e questo importato
 * qui.
 * 
 * @author Vittorio Valent
*/
@NgModule({
  declarations: [
    AppComponent,
    SchedeListComponent,
    SchedaComponent,
    UtenteVotanteComponent,
    VotazioneComponent,
    UserProfiloComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HttpClientModule,
    LayoutModule,
    AdminModule,
    UserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
