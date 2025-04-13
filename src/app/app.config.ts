import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { RouterModule } from '@angular/router'; // Import RouterModule

const routes: Routes = [
  { path: 'categories', component: CategoryListComponent },
  { path: 'categories/new', component: CategoryFormComponent },
  { path: 'categories/:id', component: CategoryDetailsComponent },
  { path: 'categories/edit/:id', component: CategoryFormComponent }, // Edycja kategorii
  { path: 'categories/:id/books/new', component: BookFormComponent }, // Dodawanie książki
  { path: 'categories/:categoryId/books/edit/:bookId', component: BookFormComponent }, // Edycja książki
  { path: 'categories/:categoryId/books/details/:bookId', component: BookDetailsComponent }, // Szczegóły książki
  { path: '', redirectTo: '/categories', pathMatch: 'full' },
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    importProvidersFrom(HttpClientModule),
  ],
};

