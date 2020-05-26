import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'; 
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog'; 
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AddEmployeeModalComponent } from './components/add-employee-modal/add-employee-modal.component';
import { AddEmployeeModalButton } from './components/add-employee-modal/add-employee-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    AddEmployeeModalComponent,
    AddEmployeeModalButton
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
