import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { VotingsystemFrontendSharedModule } from './shared/shared.module';
import { VotingsystemFrontendCoreModule } from './core/core.module';
import { VotingsystemFrontendAppRoutingModule } from './app-routing.module';
import { VotingsystemFrontendHomeModule } from './home/home.module';
import { VotingsystemFrontendEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faCircle, faChartBar, fas } from '@fortawesome/free-solid-svg-icons';




@NgModule({
  imports: [
    BrowserModule,
    VotingsystemFrontendSharedModule,
    VotingsystemFrontendCoreModule,
    VotingsystemFrontendHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    VotingsystemFrontendEntityModule,
    VotingsystemFrontendAppRoutingModule,
    FontAwesomeModule
  ],
  declarations: [
    MainComponent, 
    NavbarComponent, 
    ErrorComponent, 
    PageRibbonComponent, 
    FooterComponent    
  
    
  ],
  bootstrap: [MainComponent],
})
export class VotingsystemFrontendAppModule {
  constructor(
    library: FaIconLibrary
  ){
    library.addIconPacks(fas);
    library.addIcons(faCircle, faChartBar);
  }
}
