import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { BibliotekaService } from '../../services/biblioteka.service';
@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule], // Dodaj FormsModule tutaj
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  tytul: string = '';
  autor: string = '';
  isbn: string = '';
  liczbaStron: number | null = null;
  bibliotekaId: string | null = null;
  bookId: string | null = null; // ID książki (dla edycji)

  constructor(
    private bibliotekaService: BibliotekaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bibliotekaId = this.route.snapshot.paramMap.get('categoryId');
    this.bookId = this.route.snapshot.paramMap.get('bookId');

    // Jeśli edytujemy książkę, pobierz jej dane
    if (this.bookId) {
      this.bibliotekaService.getElementById(this.bookId).subscribe((book) => {
        this.tytul = book.tytul;
        this.autor = book.autor;
        this.isbn = book.isbn;
        this.liczbaStron = book.liczbaStron;
      });
    }
  }

  saveBook(): void {
    if (this.bookId) {
      // Aktualizacja istniejącej książki
      const updatedBook = {
        tytul: this.tytul,
        autor: this.autor,
        isbn: this.isbn,
        liczbaStron: this.liczbaStron,
        bibliotekaId: this.bibliotekaId,
      };

      this.bibliotekaService.updateElement(this.bookId, updatedBook).subscribe(() => {
        alert('Książka została zaktualizowana.');
        this.router.navigate([`/categories/${this.bibliotekaId}`]); // Powrót do szczegółów kategorii
      });
    } else {
      // Dodawanie nowej książki
      this.saveNewBook();
    }
  }

  private saveNewBook(): void {
    const newBook = {
      tytul: this.tytul,
      autor: this.autor,
      isbn: this.isbn,
      liczbaStron: this.liczbaStron,
      bibliotekaId: this.bibliotekaId,
    };

    this.bibliotekaService.addElement(newBook).subscribe(() => {
      alert('Nowa książka została dodana.');
      this.router.navigate([`/categories/${this.bibliotekaId}`]);
    });
  }
}