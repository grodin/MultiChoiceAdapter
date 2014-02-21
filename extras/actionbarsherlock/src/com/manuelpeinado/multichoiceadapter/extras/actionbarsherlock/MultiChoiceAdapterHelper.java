/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manuelpeinado.multichoiceadapter.extras.actionbarsherlock;

import android.content.Context;
import android.widget.BaseAdapter;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.ActionMode;
import com.manuelpeinado.multichoiceadapter.MultiChoiceAdapterHelperBase;

public class MultiChoiceAdapterHelper extends MultiChoiceAdapterHelperBase {
	private ActionMode actionMode;

	protected MultiChoiceAdapterHelper(BaseAdapter owner) {
		super(owner);
	}

	@Override
	protected void startActionMode() {
		final Context context = adapterView.getContext();
		if (!(owner instanceof ActionMode.Callback)) {
			throw new IllegalStateException("Owner adapter must implement ActionMode.Callback");
		}
		if (context instanceof SherlockActivity) {
			actionMode =((SherlockActivity) context).startActionMode((ActionMode.Callback) owner);
		} else if (context instanceof SherlockFragmentActivity) {
			actionMode =((SherlockFragmentActivity) context).startActionMode((ActionMode.Callback) owner);
		} else {
			throw new IllegalStateException("List view must belong to a SherlockActivity or a SherlockFragmentActivity");
		}
	}

	@Override
	protected void finishActionMode() {
		if (actionMode != null) {
			actionMode.finish();
		}
	}

	@Override
	protected void setActionModeTitle(String title) {
		actionMode.setTitle(title);
	}

	@Override
	protected boolean isActionModeStarted() {
		return actionMode != null;
	}

	@Override
	protected void clearActionMode() {
		actionMode = null;
	}
}
