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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public final class Plupload extends JavaScriptObject {
	static native Plupload create(JavaScriptObject settings) /*-{
		return new $wnd.plupload.Uploader(settings);
	}-*/;

	protected Plupload() {
	}

	public native void init() /*-{
		this.init();
	}-*/;

	public native void refresh() /*-{
		this.refresh();
	}-*/;

	public native File getFile(String id) /*-{
		return this.getFile(id);
	}-*/;

	public native void removeFile(File file) /*-{
		this.removeFile(file);
	}-*/;

	public final List<File> remove(int start, int count) {
		return asList(splice(start, count), File.class);
	}

	private native JsArray<File> splice(int start, int count) /*-{
		return this.splice(start, count);
	}-*/;

	public native void bind(String name, JavaScriptObject func) /*-{
		return this.bind(name, func);
	}-*/;

	public native void unbind(String name, JavaScriptObject func) /*-{
		return this.unbind(name, func);
	}-*/;

	public native void start() /*-{
		this.start();
	}-*/;

	public native void stop() /*-{
		this.stop();
	}-*/;

	@SuppressWarnings("unchecked")
	static <T> List<T> asList(JsArray array, Class<T> t) {
		List<T> result = new ArrayList<T>();
		for (int index = 0; index < array.length(); index++)
			result.add((T) array.get(index));
		return result;
	}
}
