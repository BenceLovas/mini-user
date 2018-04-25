import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatCardModule, MatExpansionModule, MatIconModule, MatInputModule, MatTabsModule } from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatExpansionModule,
    MatInputModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
  ],
  exports: [
    MatButtonModule,
    MatExpansionModule,
    MatInputModule,
    MatIconModule,
    MatTabsModule,
    MatCardModule,
  ],
})

export class MaterialModule { }
