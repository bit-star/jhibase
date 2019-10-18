import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IHistorySearch, HistorySearch } from 'app/shared/model/history-search.model';
import { HistorySearchService } from './history-search.service';

@Component({
  selector: 'jhi-history-search-update',
  templateUrl: './history-search-update.component.html'
})
export class HistorySearchUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    searchConetnt: [],
    searchCount: [],
    isHot: [],
    createdDate: [],
    updateDate: []
  });

  constructor(protected historySearchService: HistorySearchService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ historySearch }) => {
      this.updateForm(historySearch);
    });
  }

  updateForm(historySearch: IHistorySearch) {
    this.editForm.patchValue({
      id: historySearch.id,
      searchConetnt: historySearch.searchConetnt,
      searchCount: historySearch.searchCount,
      isHot: historySearch.isHot,
      createdDate: historySearch.createdDate != null ? historySearch.createdDate.format(DATE_TIME_FORMAT) : null,
      updateDate: historySearch.updateDate != null ? historySearch.updateDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const historySearch = this.createFromForm();
    if (historySearch.id !== undefined) {
      this.subscribeToSaveResponse(this.historySearchService.update(historySearch));
    } else {
      this.subscribeToSaveResponse(this.historySearchService.create(historySearch));
    }
  }

  private createFromForm(): IHistorySearch {
    return {
      ...new HistorySearch(),
      id: this.editForm.get(['id']).value,
      searchConetnt: this.editForm.get(['searchConetnt']).value,
      searchCount: this.editForm.get(['searchCount']).value,
      isHot: this.editForm.get(['isHot']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      updateDate:
        this.editForm.get(['updateDate']).value != null ? moment(this.editForm.get(['updateDate']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistorySearch>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
