/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package plupload.client;

import java.util.List;

public interface PluploadListener {

	void onInit(Plupload p, String runtime);

	void postInit(Plupload p);

	void onFilesAdded(Plupload p, List<File> files);

	void onFilesRemoved(Plupload p, List<File> files);

	void onQueueChanged(Plupload p);

	void onRefresh(Plupload p);

	void onStateChanged(Plupload p);

	void onFileUpload(Plupload p, File file);

	void onFileUploadProgress(Plupload p, File file);

}
