import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BibliotekaService } from '../../services/biblioteka.service';

import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule], // Dodaj FormsModule
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss'],
})

export class CategoryListComponent {
  categories: any[] = []; // Typ kategorii, zmień jeśli masz zdefiniowany model
  editedCategory: any = null;


  constructor(private bibliotekaService: BibliotekaService) {}

  ngOnInit(): void {
    this.bibliotekaService.getCategories().subscribe((data) => {
      this.categories = data;
    });
  }

  deleteCategory(id: string): void {
    this.bibliotekaService.deleteCategory(id).subscribe(() => {
      this.categories = this.categories.filter(category => category.id !== id);
    });
  }

  editCategory(category: any): void {
    console.log('Edytuj kategorię:', category); // Sprawdź obiekt
    this.editedCategory = { ...category }; // Kopiuj dane kategorii
    console.log('EditedCategory ustawione na:', this.editedCategory);
  }
  

  saveCategory(): void {
    if (this.editedCategory && this.editedCategory.id) {
      this.bibliotekaService.updateCategory(this.editedCategory.id, this.editedCategory).subscribe(() => {
        alert('Kategoria została zaktualizowana!');
        this.editedCategory = null;
        this.ngOnInit(); // Odśwież listę kategorii
      });
    }
  }

  cancelEdit(): void {
    this.editedCategory = null;
  }
  
}
